import styles from "./Recruit.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_MainPage/TeamCard';
import UserCard from '../../components/_MainPage/UserCard';
import Multiselect from "../../components/_Tool/Multiselect";

export default function Recruit() {

    const [language, setLanguage] = useState([]);
    const [field, setField] = useState([]);
    const [personality, setPersonality] = useState([]);
    const [major, setMajor] = useState();

    const [fetchData, setFetchData] = useState([]);

    const Data = {
        languageS : language,
        fieldS : field,
        personalityS : personality,
        majorLecture : major
    };
    
    let token = localStorage.getItem('token') || '';

    const RecruitSearch = async() => {
        try {
            fetch('http://localhost:8080/recruitPost/search', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(Data),
                }).then(res=>res.json())        
                .then(res=> {
                console.log(res);
                setFetchData(res);
        });

            } catch (error) {
                console.error("Failed to fetch data: ", error);
            }
        };


    return (
        <div className={styles.default}>
                <Header />
                <Sidebar/>
                <div className={styles.inner}>
                    <div className={styles.body}>
                    <div className={styles.box__}>
                        <div className={styles.first_box}>
                            <div className={styles.title_text}>함께듣는 강의를 위한 동개를 모집 하고 있어요!</div>
                            <div className={styles.search}>
                                <img className={styles.search_icon} src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
                                <input type="text" placeholder="검색어 입력" />
                            </div>
                            <div className={styles.formGroup}>
                            </div>
                        </div>
                        <div className={styles.second_box}>
                            <div className={styles.title_text}>대외적으로 프로젝트 진행을 위한 동개를 모집 하고 있어요!</div>
                            <div className={styles.search}>
                                <img className={styles.search_icon} src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
                                <input type="text" placeholder="검색어 입력" />
                            </div>
                            <div className={styles.formGroup}>
                            </div>
                        </div>
                    </div>
                        <div className={styles.title_text}>필터로 검색하기</div>
                        <div className={styles.filter_box}>
                            <Multiselect />
                            //멀티셀렉트 반영 필요
                        </div>
                    </div>
                </div>
            </div>
    );
}
