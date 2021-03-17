package com.gbgs.edu.finrep.service;

import com.gbgs.edu.finrep.constant.AppConstants;
import com.gbgs.edu.finrep.jdbc.DealRepository;
import com.gbgs.edu.finrep.model.Exposure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FinrepService {

    @Autowired
    private DealRepository dealRepository;

    private static final Logger logger = LoggerFactory.getLogger(FinrepService.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

	public void generateFinrepReport(String scope, String date) {
		Exposure exp = dealRepository.getExposure();
		logger.info(String.format("Exposure -> %s", exp));
		this.kafkaTemplate.send(AppConstants.TOPIC_NAME_USER_LOG, exp);
	}

    public void generateFinrepReport(String date) {
        Exposure exp = dealRepository.getExposure();
        logger.info(String.format("Exposure -> %s", exp));
        this.kafkaTemplate.send(AppConstants.TOPIC_NAME_USER_LOG, exp);
    }


}
