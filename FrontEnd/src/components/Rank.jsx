import React, { useState, useEffect } from 'react';

const Rank = ({ rankNo, rank, id, field, tier}) => {

    const rankMap = async(e) => {
    return (
        <> 
            <div>{rankNo} {rank} {id} {field} {tier} </div>
        </>
    );
};
};

export default Rank;