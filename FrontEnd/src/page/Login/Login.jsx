import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './style.css';
import donggae from '../../image/donggae.png';
import github from '../../image/GitHub.png';

export default function Login() {
    const navigate = useNavigate();

    // 환경 변수 설정 필요!!
    const clientId = 'f891db0dacbd43f99b74';
    const redirectUri = 'http://localhost:3000/auth/github/callback';
    const GITHUB_AUTH_URI = `https://github.com/login/oauth/authorize?client_id=${clientId}&redirect_uri=${encodeURIComponent(redirectUri)}`;

    // 로그인 버튼 클릭 핸들러
    const handleLogin = () => {
        // GitHub 인증 URI로 리다이렉션
        window.location.href = GITHUB_AUTH_URI;
    };

    // 회원가입 페이지로 이동하는 함수
    const navigateToSignup = () => {
        navigate("/Signup");
    };

    return (
        <div className="login-page">
            <div className="div">
                <div className="overlap">
                    <div className="group" />
                    <button className="text-wrapper" onClick={handleLogin}>로그인</button>
                    <div className="text-wrapper-2">
                        <button size="large" className="button" onClick={navigateToSignup}>회원가입</button>
                    </div>
                    <img className="image" alt="GitHub login" src={github} />
                </div>
                <div className="overlap-group-wrapper">
                    <div className="overlap-group">
                        <div className="text-wrapper-3">Donggae</div>
                        <p className="p">
                            <span className="span">동</span>
                            <span className="text-wrapper-4">국대</span>
                            <span className="span"> 개</span>
                            <span className="text-wrapper-4">발자들</span>
                        </p>
                    </div>
                </div>
                <img className="img" alt="Donggae" src={donggae} />
                <div className="text-wrapper-5">프로젝트 팀원을 찾으세요!</div>
            </div>
        </div>
    );
}