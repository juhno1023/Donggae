import React, { useState, useEffect } from 'react';
import styles from "./Rank.module.css"

const Rank = ({ rankNo, rank, id, field, score, tier}) => {

    return (
        <> 
            <tr>
                <td className={styles.number}>{rankNo}</td>
                <td className={styles.name}>{id}</td>
                <td className={styles.points}>{score}<img class="gold-medal" src="https://github.com/malunaridev/Challenges-iCodeThis/blob/master/4-leaderboard/assets/gold-medal.png?raw=true" alt="gold medal"/></td>
                <td className={styles.name}>{field}</td>
                <td className={styles.name}>{tier}</td>
                    
             </tr>
        </>
    );
};

export default Rank;