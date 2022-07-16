package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    // 테스트를 진행 할때마다 clearStore 를 해서 매번 테스트에 영향이 없게해준다.
    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        memoryMemberRepository.save(member);

        Member result = memoryMemberRepository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findById() {
        Member member1 = new Member();
        member1.setName("spring1");
        memoryMemberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memoryMemberRepository.save(member2);

        Member result1 = memoryMemberRepository.findByName("spring1").get();
        Member result2 = memoryMemberRepository.findByName("spring2").get();

        assertThat(result1).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
    }
}
