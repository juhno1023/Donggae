import React, { useState, useEffect } from 'react';

const Selection = ({ name, rank, id, value}) => {

    let token = localStorage.getItem('token') || '';

    const user = {
        // teamId: sessionStorage.getItem('teamId')
        // 세션 스토리지가 아니라 URL로 받아오는 것 : 팀 상세보기 클릭 후 나오는 화면이기 때문에
        // 임시로 2라 설정함
        teamId: 2,
    };

    const clickSelection = async(e) => {
        try {
            const response = await fetch("http://localhost:8080/team/selection", {
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
                alert("팀원 선택 완료");
                window.location.reload();
            } else if (response.status === 400) {
                alert(`이미 추가한 user입니다.`);
            }
        } catch (error) {
            console.error("팀원 선택 실패 : ", error);
        }
    };

    return (
        <> 
            <div>팀원 : {name} {rank}
            <label>
                <input type='button'
                    value={value[0]}
                    style={{ background: '#9BC3FF' }}
                    onClick={clickSelection}
                    />
            </label>
            <label>
                <input type='button'
                    value={value[1]}
                    style={{ background: '#B9B9B9' , width : '80px'}}
                    onClick={clickSelection}
                    />
            </label>
            </div>
        </>
    );
};

export default Selection;