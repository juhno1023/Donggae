import styles from './Card.module.css';
import { Link } from 'react-router-dom';
import React, { useState ,useEffect} from 'react';

const TeamCard = ({teamName, name, title, info, recruitPostId}) => {


    

    return (
    <>
        <div className={styles.SuggetCard}>
            <div className={styles.title}><Link to={`/post/${recruitPostId}`}>
                {teamName} </Link> 
            </div>
            <div className={styles.UserCate}>{title}</div>
            <div className={styles.UserCate}>제안한 팀장</div>
            <div className={styles.UserCateInfo}>{info.name} {info.bojRank}
            {info.donggaeRank} {name}</div> 
        </div>
    </>
    );
};

export default TeamCard;