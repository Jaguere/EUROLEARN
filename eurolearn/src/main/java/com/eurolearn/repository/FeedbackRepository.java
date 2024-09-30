package com.eurolearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eurolearn.models.FeedbackModel;

public interface FeedbackRepository extends JpaRepository<FeedbackModel, Integer> {

}
