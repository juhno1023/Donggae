import { useNavigate } from "react-router-dom";
import React, {useEffect, useState} from "react";
import "./style.css";
import donggae from '../../image/donggae.png';
import github from '../../image/GitHub.png';

export default function Signup() {
    const navigate = useNavigate();

    const [answerCodeValue, setAnswerCode] = useState('');
    const [codeValue, setCode] = useState('');
    const [emailValue, setEmail] = useState('');
    const [githubIdValue, setGithubId] = useState('');
    // 버튼 상태
    const [mailValid, setMailValid] = useState(false);
    const [codeValid, setCodeValid] = useState(false);

    const userData = {
        githubName : githubIdValue,
        dguEmail : emailValue
    }
    
    const saveCode = event =>{
        setCode(event.target.value);
        console.log(event.target.value);
    }

    const saveId = event =>{
        setGithubId(event.target.value);
        console.log(event.target.value);
    }

    const saveEmail = event =>{
        setEmail(event.target.value);
        console.log(event.target.value);
    }

    const checkDup = async (e) => {
        e.preventDefault();
        console.log(githubIdValue);
        if(githubIdValue<3){
            alert('깃허브 ID를 제대로 입력해주세요')
            return;
        }
        try {
            const response = await fetch("http://localhost:8080/valid/githubid", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(githubIdValue),
            });
    
            console.log("Response status:", response.status);
            
            if (response.ok) {
                setMailValid(true);
                alert("중복확인 완료");
            } else if (response.status === 400) {
                const errorText = await response.text();
                alert(`중복된 ID입니다.`);
            } else {
                console.error("중복확인에 실패하였습니다.", response.statusText);
            }
        } catch (error) {
            console.error("중복확인 실패3 : ", error);
        }//엄!
    };
    

    const codeSend = () => { //GET 요청 하고 JSON 받아오기
        if(mailValid){
            fetch('http://localhost:8080/sendemail', {
                method : "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: emailValue
                })
            }).then(res=>res.json())        
                .then(res=> {
                setAnswerCode(res.number);
                console.log(res)
                setCodeValid(true);
            });
            alert("메일이 보내졌습니다! 두 번 누르지 마세요")
            //메일이 보내졌습니다.
        }
    }

    const compareValue = () => {
        if(codeValid){
            if(codeValue === answerCodeValue) alert("인증 완료되었습니다.")
            else alert("인증 실패하였습니다.")
        }
    }
    
    const sign = async (e) => {
        e.preventDefault();
    
        console.log(userData);
    // 코드 확인 과정 추가
        try {
            const response = await fetch("http://localhost:8080/member/signup", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(userData),
            });
    
            console.log("Response status:", response.status);
            
            if (response.ok) {
                alert("회원가입에 성공하셨습니다.");
                window.location.replace("/");
            } else if (response.status === 400) {
                const errorText = await response.text();
                alert(`일치하지 않습니다. ${errorText}`);
                console.error("회원가입 실패 : ", errorText);
            } else {
                console.error("회원가입 실패 : ", response.statusText);
            }
        } catch (error) {
            console.error("회원가입 실패 : ", error);
        }
    };
    
    

    return (
        <div className="signup">
        <div className="div">
            <div className="overlap">
            <div className="group">
                <div className="rectangle" />
            </div>
            <button onClick={sign} className="text-wrapper, rectangle">회원가입</button>
            </div>
            <div className="overlap-group">
            <div className="overlap-group-wrapper">
                <div className="overlap-group-2">
                <div className="text-wrapper-2">Donggae</div>
                <p className="p">
                    <span className="span">동</span>
                    <span className="text-wrapper-3">국대</span>
                    <span className="span"> 개</span>
                    <span className="text-wrapper-3">발자들</span>
                </p>
                </div>
            </div>
            <img className="image" alt="Image" src={donggae} />
            </div>
            <div className="text-wrapper-4">깃허브 ID</div>
            <img className="line" alt="Line" src="line-1.svg" />
            <div className="overlap-2">
            <div className="rectangle-wrapper">
                <div className="" />
            </div>
             <input value={githubIdValue} onChange={saveId} className = "rectangle-2" Type="text" id="team_name"
                        placeholder="깃허브 ID를 작성해주세요." />
            </div>
            <div>
            <button onClick={checkDup}  className="text-wrapper-7, div-wrapper">중복확인</button>
            </div>
            <div className="text-wrapper-8">동국대 이메일</div>
            <div className="overlap-3">
            <div className="group-2">
                <input  value={emailValue} onChange={saveEmail} className = "rectangle-3" Type="text" id="team_name"
                        placeholder="메일을 입력해주세요." />
            </div>
            
            <div className="group-3">
                <div className="rectangle-4" />
            </div>
            <div className="dgu-ac-kr">@&nbsp;&nbsp;&nbsp;&nbsp;dgu.ac.kr</div>
            </div>
            <div>
            <button
                onClick={codeSend}
                className={`overlap-4 div-wrapper ${mailValid ? '' : 'disabled'}`}
                disabled={!mailValid}>
            메일발송
            </button>
            </div>
            <div className="text-wrapper-9">인증번호</div>
            <div className="overlap-5">
            <div className="rectangle-wrapper">
                <input value={codeValue} onChange={saveCode} className = "rectangle-2" Type="text" id="team_name"
                        placeholder="내용을 입력해주세요." />
            </div>
            </div>
            <div>
            <button
                onClick={compareValue}
                className={`overlap-6 div-wrapper ${codeValid ? '' : 'disabled'}`}
                disabled={!codeValid}>
            인증
            </button>
            </div>
            <img className="img" alt="Image" src={github} />
        </div>
        </div>
    );

};