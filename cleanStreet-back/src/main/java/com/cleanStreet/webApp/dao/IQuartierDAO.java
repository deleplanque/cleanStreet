package com.cleanStreet.webApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanStreet.webApp.entite.Quartier;

public interface IQuartierDAO extends JpaRepository<Quartier, Long>{

}
