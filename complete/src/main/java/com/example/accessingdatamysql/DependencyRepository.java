package com.example.accessingdatamysql;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.Dependency;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DependencyRepository extends CrudRepository<Dependency, Integer> {

    @Query(value = "SELECT MAX(Id) FROM dependency;", nativeQuery = true)
    Integer getLastId();

    @Query(value = "SELECT max(Id) FROM dependency WHERE artifact_Id=?1", nativeQuery = true)
    Integer getIdByArtifact(String artifactId);

    @Query(value = "SELECT aritfact_Id FROM dependency WHERE id=?1", nativeQuery = true)
    String getArtifactIdById(Integer id);
}
