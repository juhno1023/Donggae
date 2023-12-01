import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function AuthCallback() {
    const navigate = useNavigate();
    const code = new URL(window.location.href).searchParams.get("code");

    useEffect(() => {
        if (!code) {
            navigate('/'); //로그인 페이지로
            return;
        }

        fetch(`/auth/github/callback?code=${code}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (response.status === 404) {
                    // 회원가입 안 했을 시 NOT FOUND 처리
                    navigate('/signup');
                    alert("회원가입을 해주세요");
                    return Promise.reject('회원가입 필요');
                }
                if (!response.ok) {
                    // 에러 처리
                    return Promise.reject(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                if (data.token) {
                    localStorage.setItem('profile', data.profileUrl);
                    localStorage.setItem('token', data.token);
                    navigate('/main', { replace: true }); //로그인 성공 시 메인페이지
                }
            })
            .catch(error => {
                console.error('Error fetching token:', error);
            })
            .finally(() => {
                // URL에서 code 제거
                window.history.replaceState(null, '', window.location.pathname);
            });
    }, [navigate]);

    //return <div>Loading...</div>;
}

export default AuthCallback;
