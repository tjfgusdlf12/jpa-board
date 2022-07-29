package jpaTestBoard.jpaboard.Impl;

import jpaTestBoard.jpaboard.Common.Utils.RestResult;
import jpaTestBoard.jpaboard.Common.error.BadResulException;
import jpaTestBoard.jpaboard.Dto.Common.CommonIdx;
import jpaTestBoard.jpaboard.Dto.Common.UserInfo;
import jpaTestBoard.jpaboard.Dto.ReqMember;
import jpaTestBoard.jpaboard.Entity.Member;
import jpaTestBoard.jpaboard.Entity.Team;
import jpaTestBoard.jpaboard.Repository.TeamRepo.TeamRespository;
import jpaTestBoard.jpaboard.Repository.UserRepo.MemberRepository;
import jpaTestBoard.jpaboard.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;
    private final TeamRespository teamRespository;
    private final BCryptPasswordEncoder encoder;

    public RestResult joinMember(UserInfo userInfo) {
        RestResult result = RestResult.getDefResult();
        try {
            Timestamp now =Timestamp.valueOf(LocalDateTime.now());

            Member member = new Member();

            member.setUserId(userInfo.getUserId());
            member.setPassword(encoder.encode(userInfo.getPassword()));
            member.setUserName(userInfo.getUserName());
            member.setAge(userInfo.getAge());
            member.setEmail(userInfo.getEmail());
            member.setGender(userInfo.getGender());
            member.setUserPhone(userInfo.getUserPhone());
            member.setJoinRegDt(now);

            memberRepository.save(member);

        } catch (Exception e) {
            logger.error("",e);
            result.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }


    @Transactional(readOnly = true)
    public RestResult findMySymReviewIdxList(CommonIdx commonIdx) {
        RestResult result = RestResult.getDefResult();
        try{
            Member memberList = memberRepository.findById(commonIdx.getId()).orElseThrow(()->new BadResulException("맴버가 존재하지 않습니다."));
            String teamName = memberList.getTeam().getName();

            result.addObject("data",memberList);
        } catch (Exception e){
            logger.error("",e);
            result.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }


    public RestResult resistMember(ReqMember reqMember) {
        RestResult result = RestResult.getDefResult();
        try{
            Team team = teamRespository.findByName(reqMember.getTeamName());
            Member member = new Member();
            if(team == null){
                 member = Member.insertMember2(reqMember);
            } else {
                 member = Member.insertMember(reqMember,team);
            }
            memberRepository.save(member);
        } catch (Exception e){
            logger.error("",e);
            result.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        result.addObject("result",true);
        return result;
    }

}
