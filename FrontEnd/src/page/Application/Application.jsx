import React, { useState, useEffect } from 'react';
import styles from "./Application.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import bgImg from '../../image/donggae.png';

export default function Posting() {

    let token = localStorage.getItem('token') || '';

    const [selfIntroValue, setSelfIntro] = useState('');
    const [contentValue, setContent] = useState('');
    const [RecruitPostIdValue, setRecruitPostId] = useState('');

    const Data = {
        selfIntro : selfIntroValue,
        content : contentValue,
        recruitPostId : 2 //예시
    }

    const saveSelfIntro = event =>{
        setSelfIntro(event.target.value);
        console.log(event.target.value);
    }

    const saveContent = event =>{
        setContent(event.target.value);
        console.log(event.target.value);
    }

    const saveRecruitPostId = event =>{
        setRecruitPostId(event.target.value);
        console.log(event.target.value);
    }

    const history = useNavigate();

    

    const Applicate = (e) => {
        e.preventDefault();
        console.log(Data);
        const fetchData = async() => {
            try {
                const res = await fetch('http://localhost:8080/apply', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(Data),
                })
                if (res.ok) {
                    alert("지원 완료");
                } 
                else if (res.status === 400) {
                    alert(`지원에 실패하였습니다.`);
                } else {
                    console.error("중복확인에 실패하였습니다.", res.statusText);
                }
                
                
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
    };
    
    const [userInfo, setUserInfo] = useState([]);
    const [userRecuritField, setUserRecruitField] = useState([]);
    const [userRecuritLan, setUserRecruitLan] = useState([]);
    const [userRecuritPers, setUserRecruitPers] = useState([]);
    useEffect(() => {
        const getUserInfo = async() => {
            try {
                fetch('/applypage', {
                    method: 'GET', // 사용하는 HTTP 메서드 (GET, POST, PUT 등)
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    console.log(res)
                    setUserInfo(res)
                    setUserRecruitField(res.userInterestFieldDTOS)
                    setUserRecruitLan(res.userLanguageDTOS)
                    setUserRecruitPers(res.userPersonalityDTOS)
                });
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        getUserInfo();   
    }, []);

    return (
        <div className={styles.default}>
            <Header /><Sidebar/>
            <div className={styles.inner}>
                <div className={styles.body}>
                <form /*onSubmit={handleSubmit}*/>
                    <button type="submit" onClick={Applicate} className={styles.submitBtn}>지원완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                        <label  className={styles.text__1}>자기소개</label>
                        <input
                            value={selfIntroValue} 
                            onChange={saveSelfIntro}
                            type="text"
                            id="introduce"
                            name="introduce"
                            placeholder="간단한 자기 소개를 작성해주세요."
                            //value={formData.introduce}
                            //onChange={handleInputChange}
                        />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                        <label>지원동기 및 나의 역량</label>
                        <textarea
                            value={contentValue} 
                            onChange={saveContent}
                            id="ability"
                            name="ability"
                            placeholder="내 기술 스택, 지원 동기, 하고 싶은 이유, 이 프로젝트를 위한 나만의 아이디어, ..."
                            //value={formData.ability}
                            //onChange={handleInputChange}
                        />
                    </div>
                </form>
                
                <div className={styles.box__}>
                    <div className={styles.half}>
                        <div className={styles.text__1}>내 정보</div>
                        <div className={styles.profile_box}>
                            <div className={styles.logo}>
                            <img className={styles.logoimg} src={bgImg} alt="Donggae Logo" />
                            <div className={styles.profile_info}>
                                <div className={styles.text__2}>{userInfo.githubName}</div>
                                
                                <br></br>
                                {userInfo.selfIntro}
                            </div>
                            </div>
                        </div>
                        <div className={styles.profile_more}>
                            <div className={styles.keyword_box}>
                                <div className={styles.keyword}>
                                    모집 분야
                                    {userRecuritField ? userRecuritField.slice(0, 2).map((item, index) => (<span>{item.field}</span>)) : null}
                                </div>
                                <div className={styles.keyword}>
                                    선호 언어
                                    {userRecuritLan ? userRecuritLan.slice(0, 2).map((item, index) => (<span key={index}>{item.language}</span>)) : null}

                                </div>
                            </div>
                            <div className={styles.keyword_box}>
                                <div className={styles.keyword}>
                                    성격 특성
                                    {userRecuritPers ? userRecuritPers.slice(0, 2).map((item, index) => (<span key={index}>{item.personality}</span>)) : null}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>

    );
}