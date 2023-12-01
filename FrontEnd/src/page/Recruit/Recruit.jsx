import styles from "./Recruit.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_MainPage/TeamCard';
import UserCard from '../../components/_MainPage/UserCard';
// import Multielect from "../../components/_Tool/Multiselect";

import { MultiSelect } from "react-multi-select-component";
export default function Recruit() {

    const [language, setLanguage] = useState([]);
    const [field, setField] = useState([]);
    const [personality, setPersonality] = useState([]);
    const [major, setMajor] = useState();

    const [fetchData, setFetchData] = useState([]);
    const datas = [
        { name: "BackEnd", id: 1 },
        { name: "FrontEn", id: 2 },
        { name: "iOS", id: 3 },
        { name: "AI", id: 4 },
        { name: "Option 5", id: 5 },
        { name: "AUIUXI", id: 6 },
      ];
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

    const options = [
        { label: "Grapes 🍇", value: "grapes" },
        { label: "Mango 🥭", value: "mango" },
        { label: "Strawberry 🍓", value: "strawberry", disabled: true },
        ];
    const [selected, setSelected] = useState([]);

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
                        <div className={styles.first_box}>
                            <div className={styles.title_text}>대외적으로 프로젝트 진행을 위한 동개를 모집 하고 있어요!</div>
                            <div className={styles.search}>
                                <img className={styles.search_icon} src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png" />
                                <input type="text" placeholder="검색어 입력" />
                            </div>
                            <div className={styles.formGroup}>
                            </div>
                        </div>
                    </div>
                    <div className={styles.second_box}>
                        <div className={styles.title_text}>필터로 검색하기</div>
                        <div>
                            <h1>Select Fruits</h1>
                            <pre>{JSON.stringify(selected)}</pre>
                            <MultiSelect
                                options={options}
                                value={selected}
                                onChange={setSelected}
                                labelledBy="Select"
                            />
                            </div>
                    </div>
                    </div>
                </div>
            </div>
    );
}
