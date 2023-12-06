import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';
import DongD from '../../image/DongDonggae.png';
import BronzeD from '../../image/BronzeDonggae.png';
import SilverD from '../../image/SilverDonggae.png';
import GoldD from '../../image/GoldDonggae.png';
import DiamondD from '../../image/DiamondDonggae.png';
import Bronze2 from '../../image/Bronze2.svg';
import Silver2 from '../../image/Silver2.svg';
import Silver1 from '../../image/Silver1.svg';
import Gold5 from '../../image/Gold5.svg';
import Unrated from '../../image/Unrated.svg';


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



const TeamCard = ({teamName, name, title, info, recruitPostId}) => {

    const imageMap = {
        '다이아동개': DiamondD,
        '황금동개': GoldD,
        '은동개': SilverD,
        '동동개': BronzeD,
      };
      const rankMap = {
        'Bronze_II': Bronze2,
        'Silver_II': Silver2,
        'Silver_I': Silver1,
        'Gold_V' : Gold5,
        'Unrated': Unrated,
    };

    const rankImg = (condition) => rankMap[condition];
    const selectImage = (condition) => imageMap[condition] || DongD;

    return (
    <>
        <div className={styles.SuggetCard}>
        <Link to={`/post/${recruitPostId}`}>
            <div className={styles.title_}>{teamName}</div>
            <div className={styles.UserCate}>{title}</div>
            <div className={styles.UserCate}>제안한 팀장</div>
            <div className={styles.flex}> 
                <img className={styles.profileImg} src={info.userProfile}/>
                <div>
                <img
                    className={styles.donggae_icon}
                    src={selectImage(info.donggaeRank)}
                    alt="Rank"
                /> 
                <img
                    className={styles.rankImg}
                    src={rankImg(info.bojRank)}
                    alt="Rank"
                /> 
                <span className={styles.name}>{info.name}</span>
                </div>
            </div> 
        </Link>
        </div>
    </>
    );
};

export default TeamCard;