import styles from './Card.module.css';
import React, { useState ,useEffect} from 'react';
import github from '../../image/GitHub.png';
import Bronze2 from '../../image/Bronze2.svg';
import Silver2 from '../../image/Silver2.svg';
import Unrated from '../../image/Unrated.svg';

import DongD from '../../image/DongDonggae.png';
import BronzeD from '../../image/BronzeDonggae.png';
import SilverD from '../../image/SilverDonggae.png';
import GoldD from '../../image/GoldDonggae.png';
import DiamondD from '../../image/DiamondDonggae.png';

const selectImage = (condition) => {
    // 조건에 따라 다른 이미지를 선택하는 함수
    if (condition === '다이아동개') {
      return DiamondD;
    } 
    else if (condition === '황금동개') {
        return GoldD;
    } 
    else if (condition === '은동개') {
        return SilverD;
    }
    else if (condition === '동동개') {
        return BronzeD;
    }
    else {
        return DongD;
    };

}

const UserCard = ({ userId, name, intro, rank, donggaeRank, language, interest, personal, userProfile, isPj}) => {

    const rankMap = {
        'Bronze_II': Bronze2,
        'Silver_II': Silver2,
        'Unrated': Unrated,
    };
    const rankImg = (condition) => rankMap[condition];

    let token = localStorage.getItem('token') || '';
    // Option Select Input
    const [selectList, setSelectList] = useState([]);
    const [selected, setSelected] = useState("");
    const handleSelect = (e) => {
        setSelected(e.target.value);
    };

    const Suggestion = (e) => {
        e.preventDefault();
        if(!selected) {alert(`프로젝트를 선택해주세요`);}
        else{
        const fetchData = async() => {
            try {
                const res = await fetch(`/suggestRecruitPostEnd`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify({
                        userId:userId,
                        recruitPostId:selected,
                    }),
                })
                if (res.ok) {
                    alert("제안 성공");
                } 
                else if (res.status === 400) {
                    alert(`제안에 실패하였습니다.`);
                } else {
                    console.error("실패하였습니다.", res.statusText);
                }
                
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData(); 
         }
    };

    useEffect(() => {
        const fetchData1 = async() => {
            try {
                fetch('/suggestRecruitPost', {
                    method: 'POST', 
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                })
                .then(res=>res.json())        
                .then(res=> {
                    setSelectList(res);
                });
            } catch (error) {
                console.error("Failed to fetch: ", error);
            }
        };
        fetchData1();  
    }, []);

    const url = `https://github.com/${name}`
    return (
        <> 
            <div className={styles.UserCard}>  
                <img className={styles.profileImg} src={userProfile}/>
                <img className={styles.gitImg} onClick={()=>{window.open(url)}} alt="GitHub login" src={github}></img>
                <div className={styles.UserName}>
                <img
                    className={styles.donggae_icon}
                    src={selectImage(donggaeRank)}
                    alt="Rank"
                /> 
                <img
                    className={styles.rankImg}
                    src={rankImg(rank)}
                    alt="Rank"
                /> 
                {name}
                </div>
                
                <div className={styles.UserIntro}>{intro}</div>
                <div className={styles.UserCate}> 기술스택 </div>
                <div className={styles.UserCateInfo}> 
                {language ? language.map((per, index) => (
                    <span className={styles.name} key={index}>{index === language.length - 1 ? per : `${per}, `}</span>))
                    : null}
                 </div>
                <div className={styles.UserCate}> 관심분야 </div>
                <div className={styles.UserCateInfo}> 
                {interest ? interest.map((per, index) => (
                    <span className={styles.name} key={index}>{index === interest.length - 1 ? per : `${per}, `}</span>))
                    : null} 
                </div>
                <div className={styles.UserCate}> 개인성향 </div>
                <div className={styles.UserCateInfo}> 
                {personal ? personal.map((per, index) => (
                    <span key={index}>{index === personal.length - 1 ? per : `${per}, `}</span>))
                    : null}
                </div>
                {isPj && (
                    <>
                    <select className={styles.pjSelect} onChange={handleSelect} value={selected}>
                        <option value="0">프로젝트 이름 🍊</option>
                        {selectList ? selectList.map((item) => (
                            <option value={item.recruitPostId} key={item.recruitPostId}>
                            {item.title}
                            </option>
                        )): null}
                    </select>
                    <button onClick={Suggestion} type="submit" className={styles.submitBtn}>제안하기</button>
                    </>
                )}
            </div>
        </>
    );
};

export default UserCard;