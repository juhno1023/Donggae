import React, { useState, useEffect} from 'react';
import styles from "./Mypage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import donggae from '../../image/donggae.png';
import { MultiSelect } from "react-multi-select-component";

export default function Mypage() {
    const history = useNavigate();

    const languages = [
        { name: "JavaScrpit", id: 1 },
        { name: "C++", id: 2 },
        { name: "Python", id: 3 },
        { name: "Java", id: 4 },
        { name: "React", id: 5 },
        { name: "C#", id: 6 },
      ];

    const fields = [
        { name: "알고리즘", id: 1 },
        { name: "웹", id: 2 },
        { name: "모바일", id: 3 },
        { name: "BackEnd", id: 4 },
        { name: "FrontEnd", id: 5 },
      ];

    const personalitys = [
        { name: "분석적인", id: 1 },
        { name: "침착한", id: 2 },
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

    const [exp, setExp] = useState();
    const expInputChange = (e) => {
        setFormData(e.target.value);
    };


    // const DataModify= async() => {
    //     try {
    //         const res = await fetch('http://localhost:8080/mypage', {
    //             method: 'PUT',
    //             headers: {
    //                 'Content-Type': 'application/json',
    //                 'Authorization': `Bearer ${token}`
    //             },
    //         })     
    //         .then(res=> {}).then(data => {
    //             console.log(data)
    //             setRecPj(data);
    //             console.log(data)
    //         })
    //     } catch (error) {
    //         console.error("Failed to fetch: ", error);
    //     }
    // };


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
                            <p className={styles.user_email_text}>{dguEmail} @dgu.ac.kr</p>
                        </div>

                        <div className={styles.data_area}><p>언어 설정</p><MultiSelect options ={languages} placehdr = '언어 설정'/></div>
                        <div className={styles.data_area}><p>관심 분야 설정</p><MultiSelect options ={fields} placehdr = '관심 분야 설정'/></div>
                        <div className={styles.data_area}><p>자기 소개</p>
                            <textarea
                                id="myself"
                                name="content"
                                placeholder="내용을 작성해주세요."
                                value={formData}
                                onChange={handleInputChange}
                            /></div>
                        <div className={styles.data_area}><p>관심 분야 설정</p><MultiSelect options ={personalitys} placehdr = '성격 키워드'/></div>
                        <div className={styles.data_area}><p>팀플 경험 횟수</p>
                            <textarea
                                id="exp"
                                name="content"
                                placeholder="횟수를 입력해주세요."
                                value={exp}
                                onChange={expInputChange}
                            />
                        </div>
                        <div className={styles.data_area}></div>
                    </div>
                </div>
            </div>
        </div>
    );
}