import React, { useState, useEffect } from 'react';
import ApplyModal from './ApplyModal';
import { useParams } from 'react-router-dom';

const Selection = ({ name, rank, id, value}) => {

    let token = localStorage.getItem('token') || '';
    const [modalOpen, setModalOpen] = useState(false);
    const [content, setContent] = useState();
    const [selfIntro, setSelfIntro] = useState();
    const [userInfo, setUserInfo] = useState([]);
    const showModal = () => {
        setModalOpen(true);
    };
    
    let { teamId } = useParams();
    

    const clickSelection = async(e) => {
        try {
            const response = await fetch("http://localhost:8080/team/selection", {
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
                alert("팀원 선택 완료");
                window.location.reload();
            } else if (response.status === 400) {
                alert(`이미 추가한 user입니다.`);
            }
        } catch (error) {
            console.error("팀원 선택 실패 : ", error);
        }
    };

    const showApplication = async(e) => {
        try {
            fetch("http://localhost:8080/apply/show", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    userId: id,
                    teamId: teamId,
                }),
                })
                .then(res=>res.json())        
                    .then(res=> {
                    setContent(res.content);
                    setSelfIntro(res.selfIntro);
                    setUserInfo(res.previewUserInfoDTO);
                    console.log(res)
                });
                setModalOpen(true);

        } catch (error) {
            console.error("팀원 선택 실패 : ", error);
        }
    };

    return (
        <> 
        {modalOpen && <ApplyModal setModalOpen={setModalOpen} content={content} selfIntro={selfIntro} userInfo={userInfo}/>}

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
                    onClick={showApplication}
                    />
            </label>
            </div>
        </>
    );
};

export default Selection;