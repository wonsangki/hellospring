package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void AfterEach(){
        memoryMemberRepository.clearStore();
    }
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when

        Long saveId = memberService.join(member);

        //then

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void joinDuplicateMember(){

        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");
        // when

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));


 /*       try {
            Long saveId2 = memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("중복된 회원명입니다.");
        }
*/
        // when
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}