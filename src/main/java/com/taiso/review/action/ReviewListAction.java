package com.taiso.review.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiso.car.db.CarDAO;
import com.taiso.review.db.ReviewDAO;

public class ReviewListAction implements Review {

	@Override
	public ReviewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("ReviewListAction_execute() 호출");
		
//		// 세션 제어 (id)
		HttpSession session = request.getSession();
//		String id = (String) session.getAttribute("id");
//		
		ReviewForward forward = new ReviewForward();
//		if(id == null) {
//			forward.setPath("로그인 안 했을 때 이동할 주소");
//			forward.setRedirect(true);
//			return forward;
//		}

		ReviewDAO rDAO = new ReviewDAO();
		
		// 디비에 등록된 글 개수 불러오기
		int cnt = rDAO.getReviewCount();
		// 전달된 car_code 정보 저장
		int car_code = Integer.parseInt(request.getParameter("car_code"));
		
		CarDAO cDAO = new CarDAO();
		
		
		// 디비에 등록된 모든 글 들고오기
		List reviewList = rDAO.getReviewList();
		
		// 전달정보 저장
		// int rev_num = Integer.parseInt(request.getParameter("rev_num"));
		
		// 세션에 저장
		request.setAttribute("cnt", cnt);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("cDTO", cDAO.getCar(car_code));
		// session.setAttribute("rev_num", rev_num);
		
		// 페이지 이동
		forward.setPath("./review/carReviewList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
