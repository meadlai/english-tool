package com.kmboot.english.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kmboot.english.entity.Receiver;

public interface ReceiverRepository extends JpaRepository<Receiver, Long>, JpaSpecificationExecutor<Receiver> {

}
