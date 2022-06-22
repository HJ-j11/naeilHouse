package com.house.start.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagingVO {

    private int total; // 전체 게시글 수
    private int nowPage; // 현재페이지
    private int startPage; // 시작페이지
    private int endPage; // 끝페이지
    private int cntPerPage; // 페이지당 글 갯수
    private int lastPage; // 마지막페이지
    private List<?> content; // 게시글 데이터
    private int cntPage = 5; // 한 페이지에 보여지는 이동 가능한 페이지 수

    /**
     * 제일 마지막 페이지 계산
     */
    public void calcLastPage(int total, int cntPerPage) {
        setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
    }

    /**
     * 시작, 끝 페이지 계산
     */
    public void calcStartEndPage(int nowPage, int cntPage) {
        setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
        if (getLastPage() < getEndPage()) {
            setEndPage(getLastPage());
        }
        setStartPage(getEndPage() - cntPage + 1);
        if (getStartPage() < 1) {
            setStartPage(1);
        }
    }


    @Builder
    public PagingVO(int total, int nowPage, int cntPerPage) {
        setNowPage(nowPage);
        setCntPerPage(cntPerPage);
        setTotal(total);
        calcLastPage(getTotal(), getCntPerPage());
        calcStartEndPage(getNowPage(), cntPage);
    }
}
