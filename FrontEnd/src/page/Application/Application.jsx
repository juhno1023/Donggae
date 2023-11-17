import React, { useState } from 'react';
import styles from "./Application.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import bgImg from '../../image/donggae.png';

export default function Posting() {
    const history = useNavigate();
    const [formData, setFormData] = useState({
      introduce: '',
      ability: '',
    });
  
    const handleInputChange = (e) => {
      const { name, value } = e.target;
      setFormData({
        ...formData,
        [name]: value,
      });
    };
  
    const handleSubmit = (e) => {
      e.preventDefault();
      console.log('Form Data:', formData);
    };
  

    return (
        <div className={styles.default}>
            <Header />
            <div className={styles.inner}>
                <div className={styles.body}>
                <form onSubmit={handleSubmit}>
                    <button type="submit">지원완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                        <label  className={styles.text__1} for="introduce" >자기소개</label>
                        <input
                            type="text"
                            id="introduce"
                            name="introduce"
                            placeholder="간단한 자기 소개를 작성해주세요."
                            value={formData.introduce}
                            onChange={handleInputChange}
                        />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                        <label for="ability">지원동기 및 나의 역량</label>
                        <textarea
                            id="ability"
                            name="ability"
                            placeholder="내 기술 스택, 지원 동기, 하고 싶은 이유, 이 프로젝트를 위한 나만의 아이디어, ..."
                            value={formData.ability}
                            onChange={handleInputChange}
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
                                    <span class="list">JavaScript</span>
                                </div>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span class="list">C++</span>
                                </div>
                            </div>
                            <div className={styles.keyword_box}>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span class="list">성실함</span>
                                </div>
                                <div className={styles.keyword}>
                                    모집 분야
                                    <span class="list">냠냠</span>
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