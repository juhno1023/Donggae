import styles from "./Mainpage.module.css"
import React, { useEffect, useState } from 'react';
import Header from "../../components/_Layout/Header";
import Sidebar from "../../components/_Layout/Sidebars";
import TeamCard from '../../components/_MainPage/TeamCard';
import UserCard from '../../components/_MainPage/UserCard';

export default function Home() {    
    const [recommendUser, setRecUser] = useState([]);
    const [recommendPj, setRecPj] = useState([]);
    let token = localStorage.getItem('token') || '';
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
                    console.log("멤버 : ", res)
                    setRecUser(res);
                });
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };

        const fetchData = async() => {
            try {
                const res = await fetch('/recruitPost/recommend', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })     
                .then(res=> {
                    if (res.status === 400) {
                        alert(`400 Error`);
                        return;
                    }
                    if(res.status === 404){
                        alert(`포스트 영개`);
                        return;
                    }
                    return res.json();
                }).then(data => {
                    console.log("포스트 : ", data)
                    setRecPj(data);
                })
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData1();   
        fetchData(); 
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
                        <div className={styles.formGroup}>
                        {recommendUser.map(data => 
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
                        />)} 
                        </div>
                    </div>
                    <div className={styles.second_box}>
                    <div className={styles.text__1} >요즘 핫한 프로젝트</div>
                        <div className={styles.formGroup}>
                        {recommendPj.map(data => 
                        <TeamCard lecture={data.majorLectureName} 
                            name={data.userName} 
                            title = {data.title} 
                            date={data.createDate} 
                            rank={data.donggaeRank} 
                            language={data.recuritLanguages} 
                            recruitPostId = {data.recruitPostId}
                        />)} 
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
);
}