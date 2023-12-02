import React, { useState, useEffect} from 'react';
import styles from "./Mypage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import donggae from '../../image/donggae.png';
import { MultiSelect } from "react-multi-select-component";

export default function Mypage() {
    const history = useNavigate();


    const [selectedLanguages, setSelectedLanguages] = useState([]);
    const languages = [
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


    const [selectedFields, setSelectedFields] = useState([]);
    const fields = [
        { value: "BackEnd", label: "BackEnd ☘️" },
        { value: "FrontEnd", label: "FrontEnd 🌱" },
        { value: "iOS", label: "iOS 🌲" },
        { value: "Android", label: "Android 🌳" },
        { value: "AI", label: "AI 🍀" },
        { value: "Game", label: "Game 🌿" },
        { value: "UIUX", label: "UIUX 🌵" },
      ];


    const [selectedPersonalities, setSelectedPersonalities] = useState([]);
    const personalitys = [
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
      ];


    


    const[name, setName] = useState();
    const[selfIntro, setSelfIntro] = useState();
    const[bojRank, setBojRank] = useState();
    const[dguEmail, setDguEmail] = useState();
    const[userRank, setUserRank] = useState();
    const[teamExpCount, setTeamExpCount] = useState();
    const[leaderCount, setLeaderCount] = useState();
    const[devTestScore, setDevTestScore] = useState();
    const[userLanguageDTOS , setUserLanguageDTOS] =useState([]);
    const[userInterestFieldDTOS, setUserInterestFieldDTOS] = useState([]);
    const[userPersonalityDTOS, setUserPersonalityDTOS] =useState([]);
    const[userStudyFieldDTOS, setUserStudyFieldDTOS] = useState([]);

    let token = localStorage.getItem('token') || '';


    const [formData, setFormData] = useState() //자기소개 내용
    const handleInputChange = (e) => {
        setFormData(e.target.value);
    };

    const [baekjoon, setBaekjoon] = useState() //자기소개 내용
    const baekjoonInputChange = (e) => {
        setBaekjoon(e.target.value);
    };

    const selectedLanguagesV = selectedLanguages.map((data) => data.value);
    const selectedFieldsV = selectedFields.map((data) => data.value);
    const selectedPersonalitiesV = selectedPersonalities.map((data) => data.value);

    const Modify = {
        selfIntro : formData,
        userLanguages : selectedLanguagesV,
        userInterestFields : selectedFieldsV,
        userPersonalities : selectedPersonalitiesV,
    };

    const BojId = {
        baekjoonUserName : baekjoon
    }

    const BaekJoon = async() => {
        console.log(BojId);
        try {
            const res = await fetch('/mypage/addRank', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer {token}`
                },
                body: JSON.stringify(BojId),
            })     
        } catch (error) {
            console.error("Failed to fetch: ", error);
        }
    }

    const DataModify= async() => {
        console.log(Modify);
        try {
            const res = await fetch('/mypage', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer {token}`
                },
                body: JSON.stringify(Modify),
            })     
            if (res.ok) {
                alert("작성 완료");
            } 
            else if (res.status === 400) {
                alert(`수정 성공.`);
            } else {
                console.error("수정 실패.");
            }
        } catch (error) {
            console.error("Failed to fetch: ", error);
        }
    };


    useEffect(() => {
        const DataInquiry = () => {
            try {
                fetch('http://localhost:8080/mypage', {
                    method: "GET",
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    }).then(res=>res.json())        
                        .then(res=> {
                        console.log(res)
                        setName(res.githubName);
                        setSelfIntro(res.selfIntro);
                        setBojRank(res.bojRank);
                        setDguEmail(res.dguEmail);
                        setUserRank(res.userRank);
                        setTeamExpCount(res.teamExpCount);
                        setLeaderCount(res.leaderCount);
                        setDevTestScore(res.devTestScore);
                        setUserLanguageDTOS(res.userLanguageDTOS);
                        setUserInterestFieldDTOS(res.userInterestFieldDTOS);
                        setUserPersonalityDTOS(res.userPersonalityDTOS);
                        setUserStudyFieldDTOS(res.setUserStudyFieldDTOS);
                });

                } catch (error) {
                    console.error("Failed to fetch data: ", error);
                }
            };

            DataInquiry();
        },[]);



    return (
        <div className={styles.default}>
            <Header />
            <Sidebar/>
            <div className={styles.inner}>
                <div className={styles.body}>
                    <div className={styles.box__}>
                        <img className={styles.user_icon} alt="Image" src={localStorage.getItem('profile')} />
                        <div className={styles.user_box}>
                            <p className={styles.user_name_text}>{name}님</p>
                            <p className={styles.user_email_text}>{dguEmail} @ dgu.ac.kr</p>
                        </div>

                        <div className={styles.data_area}><p>언어 설정</p><MultiSelect options ={languages}
                                value={selectedLanguages}
                                onChange={setSelectedLanguages}
                                labelledBy="Select"/></div>
                        <div className={styles.data_area}><p>관심 분야 설정</p><MultiSelect options ={fields}
                                value={selectedFields}
                                onChange={setSelectedFields}
                                labelledBy="Select"/></div>
                        <div className={styles.data_area}><p>자기 소개</p>
                            <textarea
                                id="myself"
                                name="content"
                                placeholder="내용을 작성해주세요."
                                value={formData}
                                onChange={handleInputChange}
                            /></div>
                        <div className={styles.data_area}><p>관심 분야 설정</p><MultiSelect options ={personalitys} 
                                value={selectedPersonalities}
                                onChange={setSelectedPersonalities}
                                labelledBy="Select"/></div>
                        <div className={styles.data_area}><p>백준 아이디</p>
                        <textarea
                                id="baekjoon"
                                name="content"
                                placeholder="내용을 작성해주세요."
                                value={baekjoon}
                                onChange={baekjoonInputChange}
                            />
                        </div>

                        <button onClick={() => {
                        DataModify();
                        BaekJoon();
                        }}>수정하기</button>
                    </div>

                    <div className={styles.preview_box}>

                        <p className={styles.preview_title_text}>내 정보 미리보기</p>

                        <div className={styles.preview_inner_box}>
                            <div className={styles.profile_box}>
                                <div className={styles.logo}>
                                <img className={styles.logoimg} alt="Image" src={localStorage.getItem('profile')} />
                                <div className={styles.profile_info}>
                                    <div className={styles.text__2}>헤헤</div>
                                    <br></br>
                                    이익
                                </div>
                                </div>
                            </div>

                            <div className={styles.profile_more}>
                                <div className={styles.keyword_box}>
                                    <div className={styles.keyword}>
                                        모집 분야
                                    </div>
                                    <div className={styles.keyword}>
                                        선호 언어
                                    </div>
                                </div>
                                <div className={styles.keyword_box}>
                                    <div className={styles.keyword}>
                                        성격 특성
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