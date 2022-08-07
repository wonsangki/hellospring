package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

        public Long join(Member member){
            validateDuplicateMember(member);

            memberRepository.save(member);

            return member.getId();
        }

        public List<Member> findMembers(){
            return memberRepository.findAll();

        }

        public Optional<Member> findOne(Long memberId){
            return memberRepository.findById(memberId);
        }

        private void validateDuplicateMember(Member member) {
            memberRepository.findByName(member.getName()).ifPresent(m -> {
                throw new IllegalStateException("중복된 회원명입니다.");
            });
        }

}


