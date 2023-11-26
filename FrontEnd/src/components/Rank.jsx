import React, { useState, useEffect } from 'react';
import styles from "./Rank.module.css"

const Rank = ({ rankNo, rank, id, field, score, tier}) => {

    return (
        <> 
            <div className={styles.rankcontainer}>
                <div className={styles.rankinfo}>
                    {rankNo} {rank} {id} {field} {score} {tier}
                </div>
            </div>
        </>
    );
};

export default Rank;