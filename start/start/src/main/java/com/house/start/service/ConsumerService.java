package com.house.start.service;

import com.house.start.domain.Consumer;
import com.house.start.repository.ConsumerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumerService {
    private final ConsumerRepository consumerRepository;

    @Transactional
    public List<String> findConsumers() {
        return consumerRepository.findAllWith();
    }

}
