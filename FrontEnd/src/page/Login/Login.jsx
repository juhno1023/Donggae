import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import donggae from '../../image/donggae.png';
import github from '../../image/GitHub.png';
import styles from "./Login.module.css"

export default function Login() {
    const navigate = useNavigate();

    //환경 변수 설정 필요 - 설정 완료 .env 파일 참조
    const clientId = process.env.REACT_APP_clientId;
    const redirectUri = process.env.REACT_APP_redirectUri;
    const GITHUB_AUTH_URI = `https://github.com/login/oauth/authorize?client_id=${clientId}&redirect_uri=${redirectUri}`;

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
        <div className={styles.loginPage}>
            <div className={styles.div}>
                <div className={styles.logo}>
                    <img className="img" alt="Donggae" src={donggae} />
                    <div className={styles.center_text}>
                        <div className={styles.title}>Donggae</div>
                        <b>동</b>국대<b>개</b>발자들
                    </div>
                </div>
                <div className={styles.div}>
                    <br/><br/><br/>
                    <div className={styles.title_text}>프로젝트 팀원을 찾으세요!</div><br/><br/><br/>
                    <div className={styles.group} onClick={handleLogin}>
                        <div><img alt="GitHub login" src={github}></img></div>
                        <span>로그인</span>
                    </div>
                    <div className={styles.signBtn}>
                        <button className={styles.signin} onClick={navigateToSignup}>회원가입</button>
                    </div>

                </div>
            </div>
        </div>
    );
}