package com.udacity.course3.exercise10.repository;

import com.udacity.course3.exercise10.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    Optional<Member> findByLastName(String s);
    Optional<List<Member>> findAllByLastName(String s);
}
