
        package com.example.Erp.inventorymanagement.service;

import com.example.Erp.inventorymanagement.dto.SalesReturnRequest;
import com.example.Erp.inventorymanagement.dto.SalesReturnResponse;
import com.example.Erp.inventorymanagement.exception.ResourceNotFoundException;
import com.example.Erp.inventorymanagement.model.SalesReturn;
import com.example.Erp.inventorymanagement.repository.SalesReturnRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SalesReturnServiceImpl
        implements SalesReturnService {

    private final SalesReturnRepository salesReturnRepository;

    @Override
    public SalesReturnResponse create(
            SalesReturnRequest request
    ) {

        validateRefundStatus(request.getRefundStatus());

        SalesReturn salesReturn = SalesReturn.builder()
                .saleId(request.getSaleId())
                .customerId(request.getCustomerId())
                .totalAmount(request.getTotalAmount())
                .refundStatus(
                        request.getRefundStatus() == null
                                ? "PENDING"
                                : request.getRefundStatus().toUpperCase()
                )
                .notes(request.getNotes())
                .build();

        SalesReturn saved =
                salesReturnRepository.save(salesReturn);

        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getAll() {

        return salesReturnRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SalesReturnResponse getById(Integer id) {

        SalesReturn salesReturn =
                salesReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Sales return not found with id: "
                                                + id
                                )
                        );

        return mapToResponse(salesReturn);
    }

    @Override
    public SalesReturnResponse update(
            Integer id,
            SalesReturnRequest request
    ) {

        SalesReturn salesReturn =
                salesReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Sales return not found with id: "
                                                + id
                                )
                        );

        validateRefundStatus(request.getRefundStatus());

        salesReturn.setSaleId(request.getSaleId());
        salesReturn.setCustomerId(request.getCustomerId());
        salesReturn.setTotalAmount(request.getTotalAmount());
        salesReturn.setNotes(request.getNotes());

        if (request.getRefundStatus() != null) {
            salesReturn.setRefundStatus(
                    request.getRefundStatus().toUpperCase()
            );
        }

        SalesReturn updated =
                salesReturnRepository.save(salesReturn);

        return mapToResponse(updated);
    }

    @Override
    public void delete(Integer id) {

        SalesReturn salesReturn =
                salesReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Sales return not found with id: "
                                                + id
                                )
                        );

        salesReturnRepository.delete(salesReturn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getBySaleId(
            Integer saleId
    ) {

        return salesReturnRepository
                .findBySaleId(saleId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getByCustomerId(
            Integer customerId
    ) {

        return salesReturnRepository
                .findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalesReturnResponse> getByRefundStatus(
            String refundStatus
    ) {

        return salesReturnRepository
                .findByRefundStatusIgnoreCase(refundStatus)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private void validateRefundStatus(
            String refundStatus
    ) {

        if (refundStatus == null) {
            return;
        }

        String status = refundStatus.toUpperCase();

        if (!status.equals("PENDING")
                && !status.equals("APPROVED")
                && !status.equals("REFUNDED")
                && !status.equals("REJECTED")) {

            throw new IllegalArgumentException(
                    "Invalid refund status. Allowed values: "
                            + "PENDING, APPROVED, REFUNDED, REJECTED"
            );
        }
    }

    private SalesReturnResponse mapToResponse(
            SalesReturn salesReturn
    ) {

        return SalesReturnResponse.builder()
                .salesReturnId(
                        salesReturn.getSalesReturnId()
                )
                .saleId(
                        salesReturn.getSaleId()
                )
                .customerId(
                        salesReturn.getCustomerId()
                )
                .returnDate(
                        salesReturn.getReturnDate()
                )
                .totalAmount(
                        salesReturn.getTotalAmount()
                )
                .refundStatus(
                        salesReturn.getRefundStatus()
                )
                .notes(
                        salesReturn.getNotes()
                )
                .build();
    }
}

