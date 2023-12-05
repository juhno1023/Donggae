import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';
import DongD from '../../image/DongDonggae.png';
import BronzeD from '../../image/BronzeDonggae.png';
import SilverD from '../../image/SilverDonggae.png';
import GoldD from '../../image/GoldDonggae.png';
import DiamondD from '../../image/DiamondDonggae.png';

const TeamCard = ({teamName, name, title, info, recruitPostId}) => {

    const imageMap = {
        '다이아동개': DiamondD,
        '황금동개': GoldD,
        '은동개': SilverD,
        '동동개': BronzeD,
      };
      
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
                {info.bojRank}
                <span className={styles.name}>{info.name}</span>
                </div>
            </div> 
        </Link>
        </div>
    </>
    );
};

export default TeamCard;