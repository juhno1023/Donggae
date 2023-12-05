import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';

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



const TeamCard = ({teamName, name, title, info, recruitPostId}) => {


    

    return (
    <>
        <div className={styles.SuggetCard}>
        <Link to={`/post/${recruitPostId}`}>
            <div className={styles.title_}>{teamName}</div>
            <div className={styles.UserCate}>{title}</div>
            <div className={styles.UserCate}>제안한 팀장</div>
            <div className={styles.UserCateInfo}>{info.name} {info.bojRank}
            <img className={styles.image} src={selectImage(info.donggaeRank)} alt="Image" /> {name}</div> 
            </Link></div>
    </>
    );
};

export default TeamCard;