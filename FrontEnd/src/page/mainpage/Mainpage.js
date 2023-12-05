import styles from "./Mainpage.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_MainPage/TeamCard';
import UserCard from '../../components/_MainPage/UserCard';
import SuggestCard from '../../components/_MainPage/SuggestCard';

export default function Home() {    
    const [recommendUser, setRecUser] = useState([]);
    const [recommendPj, setRecPj] = useState([]);
    const [suggestPj, setsgPj] = useState([]);
    let token = localStorage.getItem('token') || '';
    localStorage.setItem('checkPost', JSON.stringify(''))
    useEffect(() => {
        const fetchData1 = async() => {
            try {
                fetch('/member/recommend', {
                    method: 'POST', 
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    setRecUser(res);
                });
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };

        const fetchData = async() => {
            try {
                fetch('/recruitPost/recommend', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })     
                .then(res=> {
                    if (res.status === 400) {
                        alert(`400 Error in recommend post`);
                        return;
                    }
                    if(res.status === 404){
                        alert(`포스트 영개`);
                        return;
                    }
                    return res.json();
                }).then(data => {
                    setRecPj(data);
                })
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        const fetchData2 = async() => {
            try {
                fetch('/recruitPost/suggest', {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })     
                .then(res=> {
                    if (res.status === 400) {
                        alert(`400 Error in suggest post`);
                        return;
                    }
                    if(res.status === 404){
                        alert(`포스트 영개`);
                        return;
                    }
                    return res.json();
                }).then(data => {
                    setsgPj(data);
                })
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData1();   
        fetchData(); 
        fetchData2();
    }, []);
  return (
    <div className={styles.default}>
        <Header />
        <Sidebar/>
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                <div className={styles.first_box}>
                    <div className={styles.text__1} >추천 동개</div>
                    <div className={styles.title_text}>함께듣는 강의를 위한 동개를 모집 하고 있어요!</div>
                    <div className={styles.formGroup}>
                    {recommendUser ? recommendUser.map(data => 
                    <UserCard 
                        userId={data.userId}
                        name={data.githubName} 
                        intro={data.intro} 
                        devTestScore={data.devTestScore} 
                        rank={data.boj_rank} 
                        language={data.recuritLanguages} 
                        interest={data.userInterestFields} 
                        personal={data.userPersonalities} 
                        study={data.userStudyFields} 
                        userProfile={data.userProfile} 
                    />): null} 
                    </div>
                </div>
                <div className={styles.first_box}>
                <div className={styles.text__1} >요즘 핫한 프로젝트</div>
                <div className={styles.title_text}>대외적으로 프로젝트 진행을 위한 동개를 모집 하고 있어요!</div>
                    <div className={styles.formGroup}>
                    {recommendPj ? recommendPj.map(data => 
                    <TeamCard lecture={data.majorLectureName} 
                        name={data.userName} 
                        title = {data.title} 
                        date={data.createDate} 
                        rank={data.donggaeRank} 
                        language={data.recruitLanguages} 
                        recruitPostId = {data.recruitPostId}
                    />): null} 
                    </div>
                </div>
                <div className={styles.second_box}>
                <div className={styles.text__1} >제안됨</div>
                <div className={styles.title_text}>제안요청을 보낸 프로젝트에요</div>

                    <div className={styles.suggestGroup}>
                    {suggestPj ? suggestPj.map(data => 
                    <SuggestCard  
                        teamName ={data.teamName}
                        name={data.userName} 
                        title = {data.title} 
                        info ={data.teamMemberPreview}
                        recruitPostId = {data.recruitPostId}
                    />): null} 
                    </div>
                </div>
            </div>
            </div>
        </div>
    </div>
);
}