import styles from "./Recruit.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_MainPage/TeamCard';
import UserCard from '../../components/_MainPage/UserCard';
import { MultiSelect } from "react-multi-select-component";
export default function Recruit() {
    
    let token = localStorage.getItem('token') || '';
    const [language, setLanguage] = useState([]);
    const [field, setField] = useState([]);
    const [personality, setPersonality] = useState([]);
    const [major, setMajor] = useState();
    const [selected, setSelected] = useState([]);
    const [fetchData, setFetchData] = useState([]);
    
    const options = [
        { value: "JavaScript", label: "JavaScript🥝" },
        { value: "TypeScript", label: "TypeScript🥥" },
        { value: "React", label: "React🍇" },
        { value: "Vue", label: "Vue🍈" },
        { value: "Svelte", label: "Svelte🍉" },
        { value: "Nextjs", label: "Next.js🍊" },
        { value: "Nodejs", label: "Node.js🍋" },
        { value: "Java", label: "Java🍍" },
        { value: "Spring", label: "Spring🥭" },
        { value: "Go", label: "Go🍎" },
        { value: "Nestjs", label: "Nest.js🍏" },
        { value: "Kotlin", label: "Kotlin🍐" },
        { value: "Express", label: "Express🍑" },
        { value: "MySQL", label: "MySQL🍒" },
        { value: "MongoDB", label: "MongoDB🍓" },
        { value: "Python", label: "Python🫐" },
        { value: "Django", label: "Django🍅" },
        { value: "php", label: "PHP🫒" },
        { value: "GraphQL", label: "GraphQL🍆" },
        { value: "Firebase", label: "Firebase🫑" },
        { value: "Flutter", label: "Flutter🥑" },
        { value: "Swift", label: "Swift🥬" },
        { value: "ReactNative", label: "React Native🥕" },
        { value: "Unity", label: "Unity🌸" },
        { value: "AWS", label: "AWS🌺" },
        { value: "kubernetes", label: "Kubernetes🌽" },
        { value: "Docker", label: "Docker🥒" },
        { value: "Git", label: "Git🥦" },
        { value: "Figma", label: "Figma🥔" },
        { value: "Zeplin", label: "Zeplin🌶️" },
        { value: "Jest", label: "Jest🍄" },
    ];
    
    const optionValues = selected.map((option) => option.value);

    const Data = {
        languageS : optionValues,
        fieldS : field,
        personalityS : personality,
        majorLecture : major
    };

    const RecruitSearch = async() => {
        console.log(Data);
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
                        <div className={styles.selectBox}> 
                            <MultiSelect
                                options={options}
                                value={selected}
                                onChange={setSelected}
                                labelledBy="Select"
                            />
                        </div>
                        <button  type="submit" className={styles.completeBtn} onClick={RecruitSearch}>
                            마감하기
                        </button>
                    </div>
                    </div>
                </div>
            </div>
    );
}
