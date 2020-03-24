package com.udacity.course3.exercise13.repository;

import com.udacity.course3.exercise13.model.Address;
import com.udacity.course3.exercise13.model.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@DataMongoTest
@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldSaveADocument()
    {
        Member member = new Member();

        // create a Member record
        member.setFirstName("Michael");
        member.setLastName("Jourdan");
        member.setAge(35);
        member.setGender("male");
        member.setBalance(500.0);
        member.setInterests(Arrays.asList("baseball"));
        member.setAddress(new Address(
                "24 Oak Breeze",
                "Miami",
                "Florida"
        ));

        // save the Member record
        System.err.println("Saving : " + member.getFirstName() + " " + member.getLastName());
        memberRepository.save(member);

        Member testMember = mongoOperations.findOne(query(where("firstName").is("Michael")), Member.class);
        assertThat(testMember.getLastName(), is(equalTo("Jourdan")));
    }


}