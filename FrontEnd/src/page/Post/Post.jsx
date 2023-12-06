import React, { useState ,useEffect } from 'react';
import styles from "./Post.module.css"
import Header from "../../components/_Layout/Header";
import Modify from './Modify';
import Sidebar from "../../components/_Layout/Sidebars";
import { Link } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import UserCard from '../../components/_Card/UserCard';

export default function Post() {

    let token = localStorage.getItem('token') || '';
    let checkPost = localStorage.getItem('checkPost') || '';
    let { recuritPostId } = useParams();

    // 글 수정
    const [recruitPost, setRecruitPost] = useState('');
    const [recuritField, setRecruitField] = useState([]);
    const [recuritLan, setRecruitLan] = useState([]);
    const [recuritPers, setRecruitPers] = useState([]);

    const [detailField, setdetailField] = useState([]);
    const [detailLan, setdetailLan] = useState([]);
    const [detailPers, setdetailPers] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
            try {
                fetch(`/recruitPost/${recuritPostId}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                }).then(res=>res.json())        
                .then(res=> {
                    console.log(res)
                    setRecruitPost(res)
                    setRecruitField(res.userInterestFieldDTOS.map(item => item.interestField))
                    setRecruitLan(res.userLanguageDTOS.map(item => item.language))
                    setRecruitPers(res.userPersonalityDTOS.map(item => item.personality));
                    setdetailField(res.recruitFields)
                    setdetailLan(res.recruitLanguages)
                    setdetailPers(res.recruitPersonalities);
                });
            } catch (error) {
                console.error("fatch to fail : ", error);
            }
        };
        fetchData(); 
    }, []);

    
    if( JSON.parse(checkPost) == 'modify'){
        return(
            <Modify post={recruitPost}></Modify>
        )
    }
    
    return(
        <div className={styles.default}>
        <Header /><Sidebar/>
        <div className={styles.inner}>
            <div className={styles.body}>
            <div className={styles.box__}>
                <Link to='/application'><button type="submit" className={styles.submitBtn}>지원하기</button></Link>
                <div  className={styles.text__1}>{recruitPost.title}</div>
                <div className={styles.conTent}>
                    <div>{recruitPost.content}</div>
                </div>
            </div>
            <div className={styles.box__}>
                <div className={styles.half}>
                <div className={styles.text__1}>팀장 정보</div>
                <div className={styles.formGroup}>
                    <UserCard 
                        userId={recruitPost.userId}
                        name={recruitPost.githubName} 
                        intro={recruitPost.intro} 
                        devTestScore={recruitPost.devTestScore} 
                        rank={recruitPost.bojRank} 
                        donggaeRank ={recruitPost.userRank} 
                        language={recuritLan} 
                        interest={recuritField} 
                        personal={recuritPers} 
                        study={recruitPost.userStudyFields} 
                        userProfile={recruitPost.userProfile} 
                        isPj ={false}
                    />
                    </div>

                
                </div>
                <div className={styles.half}>
                    <div className={styles.text__1}>프로젝트 모집 사항</div> 
                    <div className={styles.keyword}>
                    <b>모집 분야</b>
                        {detailField ? detailField.slice(0, 2).map((item, index) => (<span className={styles.span} key={index}>{item.field}</span>)) : null}
                    </div>
                    <div className={styles.keyword}>
                    <b>선호 언어</b>
                        {detailLan ? detailLan.slice(0, 2).map((item, index) => (<span className={styles.span} key={index}>{item.language}</span>)) : null}
                    </div>
                    <div className={styles.keyword}>
                    <b>선호 성향</b>
                        {detailPers ? detailPers.slice(0, 2).map((item, index) => (<span key={index}>{item.personality}</span>)) : null}
                    </div>
                </div>
                </div>
            </div>
        </div>
        </div>    
    )
}