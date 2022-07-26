package jpaTestBoard.jpaboard.Service;

import jpaTestBoard.jpaboard.Common.Utils.RestResult;
import jpaTestBoard.jpaboard.Dto.Common.CommonIdx;
import jpaTestBoard.jpaboard.Dto.Common.UserInfo;
import jpaTestBoard.jpaboard.Dto.ReqMember;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

public interface MemberService {
    RestResult findMySymReviewIdxList(CommonIdx commonIdx);

    RestResult resistMember(ReqMember reqMember);

    RestResult joinMember(UserInfo userInfo);
}
