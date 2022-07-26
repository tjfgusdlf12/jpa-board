package jpaTestBoard.jpaboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pagination {

    private Integer pageSize;

    Integer page;

    Integer block;

    Integer totalListCnt;

    Integer totalPageCnt;

    Integer totalBlockCnt;

    Integer startPage;

    Integer endPage;

    Integer prevBlock;

    Integer nextBlock;

    Integer startIndex;

    public Pagination(Integer totalListCnt, Integer page, Integer pageSize, Integer blockSize) {
        this.pageSize = pageSize;

        //현제 페이지
        this.page = page;

        //총 게시물 수
        this.totalListCnt = totalListCnt;

        //퐁 페이지 수
        totalPageCnt = (int)Math.ceil(totalListCnt * 1.0 / this.pageSize);

        //총 블럭 수
        totalBlockCnt = (int)Math.ceil(totalPageCnt * 1.0 / blockSize);

        //현제 블럭
        block = (int)Math.ceil((this.page * 1.0) / blockSize);

        startPage = ((block - 1) * blockSize + 1);

        endPage = startPage + blockSize - 1;

        //블럭 마지막 페이지
        if(endPage > totalPageCnt)endPage = totalPageCnt;

        //이전 블럭 ( 클릭시, 이전 블럭 마지막 페이지)
        prevBlock = (block * blockSize) - blockSize;

        //이전 블럭 validation
        if(prevBlock < 1)prevBlock = 1;

        //다음 블럭 validation
        if(nextBlock > totalPageCnt)nextBlock = totalPageCnt;

        startIndex = (this.page - 1) * this.pageSize;
    }
}
