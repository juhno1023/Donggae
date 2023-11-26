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

                // if () {
                //     alert(`해당 아이디가 없습니다`);
                    
                // }
                // if(){
                //     alert(`해당 모집글이 없습니다`);
                    
                // }
                
                
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
    };
    
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
                        <div className={styles.text__1}>팀장 정보</div>
                        <div className={styles.profile_box}>
                            <div className={styles.logo}>
                            <img className={styles.logoimg} src={bgImg} alt="Donggae Logo" />
                            <div className={styles.profile_info}>
                                <div className={styles.text__2}>Dabin</div>
                                vhlekqls@naver.com
                                <br></br>
                                웹프로젝트 장인입니다.
                            </div>
                            </div>
                        </div>
                        <div className={styles.profile_more}>
                            <div className={styles.keyword_box}>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span>JavaScript</span>
                                </div>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span>C++</span>
                                </div>
                            </div>
                            <div className={styles.keyword_box}>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span>성실함</span>
                                </div>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span>냠냠</span>
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