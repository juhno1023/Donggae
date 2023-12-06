import styles from './Card.module.css';
import React, { useState ,useEffect} from 'react';
import github from '../../image/GitHub.png';
import Bronze2 from '../../image/Bronze2.svg';
import Silver2 from '../../image/Silver2.svg';
import Silver1 from '../../image/Silver1.svg';
import Gold5 from '../../image/Gold5.svg';
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

const UserCard = ({ userId, name,selfIntro, content, intro, rank, donggaeRank, language, interest, personal, userProfile, isPj}) => {

    const rankMap = {
        'Bronze_II': Bronze2,
        'Silver_II': Silver2,
        'Silver_I': Silver1,
        'Gold_V' : Gold5,
        'Unrated': Unrated,
    };
    const rankImg = (condition) => rankMap[condition];
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
                <div className={styles.UserCate}> 자기소개 </div>
                <div className={styles.intro}>{content}</div>
                {selfIntro ? 
                <>
                <div className={styles.UserCate}> 지원동기 및 나의 역량 </div>
                <div className={styles.UserIntro}>{selfIntro}</div> 
                </>: null}
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

            </div>
        </>
    );
};

export default UserCard;