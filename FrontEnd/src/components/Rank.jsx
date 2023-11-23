import React, { useState, useEffect } from 'react';

const Rank = ({ rankNo, rank, id, field, score, tier}) => {

    const rankMap = async(e) => {
    return (
        <> 
            <div>{rankNo} 으악 {rank} {id} {field} {score} {tier} </div>
        </>
    );
};
};

export default Rank;