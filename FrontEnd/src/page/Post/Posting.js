import React, { useState } from 'react';
import styles from "./Posting.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";

export default function Posting() {
    const [formData, setFormData] = useState({
        team_name: '',
        post_title: '',
        post_content: '',
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
                    <button type="submit">작성완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                    <label htmlFor="team_name">팀명</label>
                    <input
                        type="text"
                        id="team_name"
                        name="team_name"
                        placeholder="팀 이름을 작성해주세요."
                        value={formData.team_name}
                        onChange={handleInputChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg2}`}>
                    <label htmlFor="post_title">제목</label>
                    <input
                        type="text"
                        id="post_title"
                        name="post_title"
                        placeholder="제목을 작성해주세요. (예시 : 함께 ㅇㅇ 프로젝트를 이끌어 갈 분들을 모집합니다! )"
                        value={formData.post_title}
                        onChange={handleInputChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                    <label htmlFor="post_content">내용</label>
                    <textarea
                        id="post_content"
                        name="post_content"
                        placeholder="내용을 작성해주세요. (예시 : 이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs, ...)"
                        value={formData.post_content}
                        onChange={handleInputChange}
                    />
                    </div>
                </form>
                
                <div className={styles.box__}>
                    <div className={styles.half}>
                        <div className={styles.text__1}>세부사항 설정</div> 모집 분야
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>JavaScript</span>
                                </label>
                            </div>
                        </div>
                        선호 언어
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>JavaScript</span>
                                </label>
                            </div>
                        </div>
                        선호 성향
                        <div className={styles.container}>
                            <div>
                                <label>
                                    <input type="checkbox" name=""/>
                                    <span className={styles.list}>JavaScript</span>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div className={styles.half}>
                    <div className={styles.text__1}>전공강의 팀원 모집하기</div> 선택 된 수강강의
                        <select>
                            <option value="Option 1">없음</option>
                            <option value="Option 2">소프트웨어공학개론</option>
                            <option value="Option 3">Option 3</option>
                            <option value="Option 4">Option 4</option>
                            <option value="Option 5">Option 5</option>
                            <option value="Option length">
                            Options
                            </option>
                        </select>
                    </div>
                </div>
                </div>
            </div>
        </div>

    );
}