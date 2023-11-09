import { useNavigate } from "react-router-dom";
import "./style.css";
import donggae from '../../image/donggae.png';
import github from '../../image/GitHub.png';

export default function Signup() {
    const navigate = useNavigate();

    return (
        <div className="signup">
        <div className="div">
            <div className="overlap">
            <div className="group">
                <div className="rectangle" />
            </div>
            <div className="text-wrapper">회원가입</div>
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
            <div className="text-wrapper-5">회원가입</div>
            <img className="line" alt="Line" src="line-1.svg" />
            <div className="overlap-2">
            <div className="rectangle-wrapper">
                <div className="" />
            </div>
             <input className = "rectangle-2" Type="text" id="team_name"
                        placeholder="팀 이름을 작성해주세요." />
            </div>
            <div>
            <button className="text-wrapper-7, div-wrapper">중복확인</button>
            </div>
            <div className="text-wrapper-8">동국대 이메일</div>
            <div className="overlap-3">
            <div className="group-2">
                <input className = "rectangle-3" Type="text" id="team_name"
                        placeholder="내용을 입력해주세요." />
            </div>
            
            <div className="group-3">
                <div className="rectangle-4" />
            </div>
            <div className="dgu-ac-kr">@&nbsp;&nbsp;&nbsp;&nbsp;dgu.ac.kr</div>
            </div>
            <div>
                <button className="overlap-4 ,div-wrapper">메일발송</button>
            </div>
            <div className="text-wrapper-9">인증번호</div>
            <div className="overlap-5">
            <div className="rectangle-wrapper">
                <input className = "rectangle-2" Type="text" id="team_name"
                        placeholder="내용을 입력해주세요." />
            </div>
            </div>
            <div>
                <button className="overlap-6 ,div-wrapper">인증</button>
            </div>
            <img className="img" alt="Image" src={github} />
        </div>
        </div>
    );

};