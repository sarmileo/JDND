package com.udacity.course3.exercise10;

import com.udacity.course3.exercise10.model.Address;
import com.udacity.course3.exercise10.model.Member;
import com.udacity.course3.exercise10.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableMongoRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository) {
        return args -> {
            // STEP 1: Define Member and MemberRepository first before changing this class
            Member member = new Member();

            // STEP 2: create a Member record
            member.setFirstName("Leo");
            member.setLastName("DaVinci");
            member.setAge(35);
            member.setGender("male");
            member.setBalance(500.0);
            member.setInterests(Arrays.asList("baseball"));
            member.setAddress(new Address(
                    "24 Oak Breeze",
                    "Miami",
                    "Florida"
            ));

            // STEP 3: save the Member record
            memberRepository.save(member);

            // read the Member using memeber last name
            System.out.println("Finding all by last name: DaVinci");
            Optional<List<Member>> members = memberRepository.findAllByLastName("DaVinci");

            members.ifPresent(theMembers -> {
                        theMembers.forEach(member1 -> {
                            System.err.println(member1);
                        });
                    });

            System.out.println("Finding member by id: ");
            Optional<Member> memberById = memberRepository.findById(member.getId());

            memberById.ifPresent(theMember -> {
                System.err.println("Member with Id: " + theMember.getId());
                System.err.println(theMember);
            });

            System.out.println("Finding all by Age > 40: ");
            List<Member> membersByAge = memberRepository.findAll().stream()
                    .filter(member1 -> member1.getAge() > 40)
                    .collect(Collectors.toList());
            membersByAge.forEach(memberFound -> {
                System.err.println(memberFound);
            });

            System.out.println("Deleting documents : ");
            members.ifPresent(theMembers -> {
                theMembers.forEach(memberFound -> {
                    memberRepository.delete(memberFound);
                    System.err.println("Member : " + memberFound.getFirstName() + " "
                                                   + memberFound.getLastName() + " ...deleted");
                });

            });

        };
    }
}