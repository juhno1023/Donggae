import React, { useState, useEffect } from 'react';

const Deletion = ({ name, rank, id, value}) => {

    let token = localStorage.getItem('token') || '';

    const user = {
        // teamId: sessionStorage.getItem('teamId')
        // 세션 스토리지가 아니라 URL로 받아오는 것 : 팀 상세보기 클릭 후 나오는 화면이기 때문에
        // 임시로 2라 설정함
        teamId: 1,
    };

    const clickDeletion = async(e) => {
        try {
            const response = await fetch("http://localhost:8080/team/deletion", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    teamId: user.teamId,
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