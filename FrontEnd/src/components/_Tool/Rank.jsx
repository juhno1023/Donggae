import React, { useState, useEffect } from 'react';
import styles from "./Rank.module.css"

import donggae from '../../image/donggae.png';

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


const Rank = ({ rankNo, rank, id, field, score, tier}) => {

    return (
        <> 
            <tr>
                <td className={styles.number}>{rankNo}</td>
                <td className={styles.number}><img
                                        src={selectImage(rank)}
                                        alt="Image"
                                        /></td>
                <td className={styles.name}>{id}</td>
                <td className={styles.points}>{score}<img class="gold-medal" src="https://github.com/malunaridev/Challenges-iCodeThis/blob/master/4-leaderboard/assets/gold-medal.png?raw=true" alt="gold medal"/></td>
                <td className={styles.name}>{field}</td>
                <td className={styles.name}>{tier}</td>
             </tr>
        </>
    );
};

export default Rank;