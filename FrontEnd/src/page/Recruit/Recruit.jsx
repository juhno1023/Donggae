import styles from "./Recruit.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_MainPage/TeamCard';
import { MultiSelect } from "react-multi-select-component";
import './style.css';


export default function Recruit() {
    
    let token = localStorage.getItem('token') || '';
    const [lanSelected, setLanSelected] = useState([]);
    const [fieSelected, setFieSelected] = useState([]);
    const [perSelected, setPerSelected] = useState([]);
    const [majSelected, setMajSelected] = useState("");
    const [recommendPj, setRecPj] = useState([]);

    const handleSelect = (e) => {
        setMajSelected(e.target.value);
    };
    
    const languageA = [
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
    const fieldA = [
        { value: "BackEnd", label: "BackEnd ☘️" },
        { value: "FrontEnd", label: "FrontEnd 🌱" },
        { value: "iOS", label: "iOS 🌲" },
        { value: "Android", label: "Android 🌳" },
        { value: "AI", label: "AI 🍀" },
        { value: "Game", label: "Game 🌿" },
        { value: "UIUX", label: "UIUX 🌵" },
    ];
    const personalityA = [
        { value: "논리적인", label: "논리적인 😺" },
        { value: "계획적인", label: "계획적인 🕊️" },
        { value: "꼼꼼한", label: "꼼꼼한 🐶" },
        { value: "신속한", label: "신속한 🐺" },
        { value: "쾌활한", label: "쾌활한 🦁" },
        { value: "창의적인", label: "창의적인 🐯" },
        { value: "성실한", label: "성실한 🦊" },
        { value: "목표지향적", label: "목표지향적 🦝" },
        { value: "끈기있는", label: "끈기있는 🐮" },
        { value: "리더", label: "리더 🐲" },
        { value: "팔로워", label: "팔로워 🐔" },
        { value: "커뮤니케이터", label: "커뮤니케이터 🦄" },
        { value: "완벽주의자", label: "완벽주의자 🐰" },
        { value: "모험가", label: "모험가 🐻" },
        { value: "발명가", label: "발명가 🐻‍❄️" },
        { value: "분석가", label: "분석가 🐼" },
        { value: "중재자", label: "중재자 🐥" },
        { value: "만능재주꾼", label: "만능재주꾼 🐇" }
    ]    
    const majorA = [
        { value: null, label: "없음" },
        { value: "어드벤처디자인", label: "어드벤처디자인 ❤️" },
        { value: "소프트웨어공학개론", label: "소프트웨어공학개론 🩷" },
        { value: "컴퓨터알고리즘과실습", label: "컴퓨터알고리즘과실습 🧡" },
        { value: "공개SW프로젝트", label: "공개SW프로젝트 💛" },
        { value: "웹프로그래밍", label: "웹프로그래밍 💚" },
        { value: "객체지향설계와패턴", label: "객체지향설계와패턴 💙" },
        { value: "컴퓨터공학종합설계1", label: "컴퓨터공학종합설계1 🩵" },
        { value: "컴퓨터공학종합설계2", label: "컴퓨터공학종합설계2 💜" },
        { value: "인간컴퓨터상호작용시스템", label: "인간컴퓨터상호작용시스템 🖤" },
        { value: "데이터분석및실습", label: "데이터분석및실습 🤍" },
    ]

    const lanValues = lanSelected.map(language => {
        const languageInfo = languageA.find(lang => lang.value === language.value);
        return languageInfo ? { language: languageInfo.value } : null;
    });
    const fieValues = fieSelected.map(field => {
        const fieldInfo = fieldA.find(fie => fie.value === field.value);
        return fieldInfo ? { interestField: fieldInfo.value } : null;
    });
    const perValues = perSelected.map(personality => {
        const perInfo = personalityA.find(per => per.value === personality.value);
        return perInfo ? { personality: perInfo.value } : null;
    });
    
    const Data = {
        languageS : lanValues,
        fieldS : fieValues,
        personalityS : perValues,
        majorLecture : majSelected.value, 
    };

    const RecruitSearch = async() => {
        if(!Data.majorLecture) Data.majorLecture = null
        console.log(Data);
        try {
            fetch('http://localhost:8080/recruitPost/search', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(Data),
                })
                .then(res=>res.json())        
                .then(res=> {
                console.log(res.naturalRecruitPosts);
                setRecPj(res.naturalRecruitPosts);
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
                    {/* <div className={styles.box__}>
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
                    </div> */}
                    <div className={styles.second_box}>
                        <div className={styles.title_text}>필터로 검색하기</div>
                        <div className={styles.search_form__alert}>
                            <div className={styles.selectBox}> 
                            language
                                <MultiSelect
                                    options={languageA}
                                    value={lanSelected}
                                    onChange={setLanSelected}
                                    labelledBy="Select"
                                    hasSelectAll = {false}
                                    disableSearch = {true}
                                />
                            </div>
                            <div className={styles.selectBox}> 
                            field
                                <MultiSelect
                                    options={fieldA}
                                    value={fieSelected}
                                    onChange={setFieSelected}
                                    labelledBy="Select"
                                    hasSelectAll = {false}
                                    disableSearch = {true}
                                />
                            </div>
                            <div className={styles.selectBox}> 
                            personality
                                <MultiSelect
                                    options={personalityA}
                                    value={perSelected}
                                    onChange={setPerSelected}
                                    labelledBy="Select"
                                    hasSelectAll = {false}
                                    disableSearch = {true}
                                />
                            </div>
                            <div className={styles.selectBox}> 
                                major
                                <br></br>
                                <select className={styles.search_form__select} onChange={handleSelect} value={majSelected}>
                                    {majorA.map(({value, label}) => (
                                        <option value={value} key={label}>
                                        {label}
                                        </option>
                                    ))}
                                </select>
                            </div>
                            <button  type="submit" className={styles.completeBtn} onClick={RecruitSearch}>
                            <input className={styles.search_form__search_icon} type="text" autoFocus />
                            </button>
                        </div>
                    </div>
                    <div className={styles.formGroup}>
                    {recommendPj ? recommendPj.map(data => 
                    <TeamCard lecture={data.majorLectureName} 
                        name={data.userName} 
                        title = {data.title} 
                        date={data.createdDate} 
                        rank={data.donggaeRank} 
                        // language={data.languageS} 
                        recruitPostId = {data.postId}
                    />): null} 
                    </div>
                    </div>
                </div>
            </div>
    );
}
