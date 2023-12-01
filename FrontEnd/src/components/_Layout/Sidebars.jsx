import { useEffect, useRef, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Sidebars.css';
import bgImg from '../../image/donggae.png';
import styles from "./Sidebar.module.css"



const Sidebar = () => {
    const navigate = useNavigate();
    
    const sidebarRef = useRef();
    const logout = () => {
        alert('로그아웃 되었습니다.')
        window.localStorage.clear();
    }
    return <div className='sidebar'>
        <div className="sidebar__logo">
        <img className="logoimg" src={bgImg} alt="Donggae Logo" />
        </div>
        <div ref={sidebarRef} className="sidebar__menu">
            <nav className={styles.navigation}>
                <ul>
                <li className={`sidebar__menu__item`}><Link to='/main'>메인</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/posting'>posting</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/leader'>leader</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/userteam'>myteam</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/rank'>rank</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/teaminfo'>내가 속한 팀</Link></li>
                <li className={`sidebar__menu__item`}><Link to='/mypage'>마이페이지</Link></li>
                <li className={`sidebar__menu__item`} onClick={logout}><Link to='/'>로그아웃</Link></li>
                </ul>
            </nav>
           
        </div>
    </div>;
};

export default Sidebar;