import styles from "./Sidebar.module.css"

import { useNavigate } from "react-router-dom";
import profile from '../../image/profile.png';

export default function Sidebar() {
    const history = useNavigate();
    return (
        <div className={styles.sidebar}>
            <div className={styles.contents}>
                <nav className={styles.sidenav}>
                    <ul>
                        <li>내가 속한 팀</li>
                        <li>마이페이지</li>
                        <li>
                            <svg xmlns="http://www.w3.org/2000/svg" width="38" height="40" viewBox="0 0 42 46" fill="none">
                            <g clip-path="url(#clip0_163_22)"><path d="M29.75 13.4167L27.2825 16.1192L31.7975 21.0833H14V24.9167H31.7975L27.2825 29.8617L29.75 32.5833L38.5 23L29.75 13.4167ZM7 9.58333H21V5.75H7C5.075 5.75 3.5 7.475 3.5 9.58333V36.4167C3.5 38.525 5.075 40.25 7 40.25H21V36.4167H7V9.58333Z" fill="#898989"/></g>
                            <defs><clipPath id="clip0_163_22"><rect width="42" height="46" fill="white"/></clipPath></defs></svg>
                        로그아웃</li>
                    </ul>
                </nav>
                <div className={styles.profilebox}>
                    <div className={styles.logo}>
                        <img className={styles.logoimg} src={profile} alt="Profile" />
                        <div>
                            <div className={styles.title}>Dabin</div>
                            <div className={styles.subtitle}>chlekqls@naver.com</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
