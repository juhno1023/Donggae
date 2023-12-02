import React, { useState  } from 'react';
import styles from "./Posting.module.css"
import { useNavigate } from "react-router-dom";
import Sidebar from "../../components/_Layout/Sidebars";
import Header from "../../components/_Layout/Header";
import CheckBox from '../../components/_Tool/CheckBox';
import { MultiSelect } from "react-multi-select-component";

export default function Posting() {
    let token = localStorage.getItem('token') || '';
    
    const [title, setTitle] = useState('');
    const [teamName, setTeamName] = useState('');
    const [content, setContent] = useState('');
    const [lanSelected, setLanSelected] = useState([]);
    const [fieSelected, setFieSelected] = useState([]);
    const [perSelected, setPerSelected] = useState([]);
    const [majSelected, setMajSelected] = useState("");
    

    const handleSelect = (e) => {
        setMajSelected(e.target.value);
    };
    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };
    
    const handleTeamNameChange = (event) => {
    setTeamName(event.target.value);
    };
    
    const handleContentChange = (event) => {
    setContent(event.target.value);
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

    const lanValues = lanSelected.map(language => language.value);
    const fieValues = fieSelected.map(field => field.value);
    const perValues = perSelected.map(personality => personality.value);

    const formData = {
        title: title,
        content: content,
        majorLectureName: majSelected,
        recruitFields: fieValues,
        recruitLanguages: lanValues,
        recruitPersonalities: perValues,
        teamName: teamName,
    };

    const PostOn = (e) => {
        e.preventDefault();
        const fetchData = async() => {
            console.log(formData);
            try {
                const res = await fetch('http://localhost:8080/recruitPost', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(formData),
                })
                if (res.ok) {
                    alert("작성 완료");
                    window.location.replace("/userteam");
                } 
                else if (res.status === 400) {
                    alert(`작성에 실패하였습니다.` );
                    
                } else {
                    console.error("작성에 실패하였습니다.", res.statusText);
                }
                
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
    };

    {}
    return (
        <div className={styles.default}>
          <Header /><Sidebar/>
          <div className={styles.inner}>
                <div className={styles.body}>
                <form onSubmit={PostOn}>
                    <button type="submit" className={styles.submitBtn}>작성완료</button>
                    <div className={`${styles.formGroup} ${styles.fmg1}`}>
                    <label>팀명</label>
                    <input
                        type="text"
                        id="team_name"
                        name="teamName"
                        placeholder="팀 이름을 작성해주세요."
                        value={teamName}
                        onChange={handleTeamNameChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg2}`}>
                    <label>제목</label>
                    <input
                        type="text"
                        id="post_title"
                        name="title"
                        placeholder="제목을 작성해주세요. (예시 : 함께 ㅇㅇ 프로젝트를 이끌어 갈 분들을 모집합니다! )"
                        value={title}
                        onChange={handleTitleChange}
                    />
                    </div>
                    <div className={`${styles.formGroup} ${styles.fmg3}`}>
                    <label>내용</label>
                    <textarea
                        id="post_content"
                        name="content"
                        placeholder="내용을 작성해주세요. (예시 : 이번에 간단하게 웹 프로젝트를 함께 이끌어 갈 분들을 모집합니다! 사용하고자 하는 기술 스택은 nodejs, ...)"
                        value={content}
                        onChange={handleContentChange}
                    />
                    </div>
                </form>
                
                <div className={styles.box__}>
                    <div className={styles.half}>
                        <div className={styles.text__1}>세부사항 설정</div>
                        <div className={styles.search_form__alert}>
                            <div className={styles.selectBox}> 
                            모집분야
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
                            선호언어
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
                            선호성향
                                <MultiSelect
                                    options={personalityA}
                                    value={perSelected}
                                    onChange={setPerSelected}
                                    labelledBy="Select"
                                    hasSelectAll = {false}
                                    disableSearch = {true}
                                />
                            </div>
                        </div>
                    </div>
                    <div className={styles.half}>
                    <div className={styles.text__1}>전공강의 팀원 모집하기</div> 선택 된 수강강의
                    <select className={styles.search_form__select} onChange={handleSelect} value={majSelected}>
                        {majorA.map(({value, label}) => (
                            <option value={value} key={label}>
                            {label}
                            </option>
                        ))}
                    </select>
                    </div>
                </div>
                </div>
            </div>
        </div>

    );
}