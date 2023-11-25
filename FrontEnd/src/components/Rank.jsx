import React, { useState, useEffect } from 'react';

const Rank = ({ rankNo, rank, id, field, score, tier}) => {

    return (
        <> 
            <div>{rankNo} {rank} {id} {field} {score} {tier} </div>
        </>
    );
};

export default Rank;