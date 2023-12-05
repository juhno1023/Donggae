import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';

const Deletion = ({ name, rank, id, value}) => {

    let token = localStorage.getItem('token') || '';

    let { teamId } = useParams();


    const clickDeletion = async(e) => {
        try {
            const response = await fetch("http://localhost:8080/team/deletion", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    teamId: teamId,
                    userId: id
                }),
            });
    
            console.log("Response status:", response.status);
            
            if (response.ok) {
                alert("팀원 추방 완료");
                window.location.reload();
            } else if (response.status === 400) {
                alert(`팀장은 추방 불가`);
            } else {
                console.error("팀장은 추방 불가합니다.", response.statusText);
            }
        } catch (error) {
            console.error("팀원 추방 실패 : ", error);
        }
    };

    return (
        <> 
            <div>팀원 : {name} {rank}
            <label>
                <input type='button'
                    value={value}
                    style={{ background: '#FF9F81' }}
                    onClick={clickDeletion}
                    />
            </label>
            </div>
        </>
    );
};

export default Deletion;